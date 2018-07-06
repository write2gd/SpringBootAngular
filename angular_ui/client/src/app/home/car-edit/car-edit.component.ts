import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {CarService} from "../../shared/car/car.service";

@Component({
    selector: 'app-car-edit',
    templateUrl: './car-edit.component.html',
    styleUrls: ['./car-edit.component.css']
})
export class CarEditComponent implements OnInit, OnDestroy {
    car: any = {};

    sub: Subscription;

    constructor(private route: ActivatedRoute,
                private router: Router,
                private carService: CarService) {
    }

    ngOnInit() {
        this.sub = this.route.params.subscribe(params => {
            const id = params['id'];
            if (id) {
                this.carService.get(id).subscribe((car: any) => {
                    console.log(car);
                    if (car) {
                        this.car = car;
                        console.log('Car with id ${id}', this.car);
                        this.car.href = car._links.self.href;
                    } else {
                        console.log('Car with id ${id} not found, returning to list');
                        this.gotoList();
                    }
                });
            }
        });
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    gotoList() {
        this.router.navigate(['/car-list']);
    }

    save(form: NgForm) {
        this.carService.save(form).subscribe(result => {
            this.gotoList();
        }, error => console.error(error));
    }

    remove(id) {
        this.carService.remove(id).subscribe(result => {
            this.gotoList();
        }, error => console.error(error));
    }
}
