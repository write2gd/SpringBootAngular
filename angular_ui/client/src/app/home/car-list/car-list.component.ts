import { CarService } from '../../shared/car/car.service';
import {Component, OnInit, ViewChild} from '@angular/core';
@Component({
  selector: 'car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})

export class CarListComponent implements OnInit {
  cars: Array<any>;
  luxuryCars: Array<any>;
  constructor(private carService: CarService) { }

  ngOnInit() {
    this.carService.getAll().subscribe(data => {
      this.cars = data;
    });
    this.getAllLuxuryCars();
  }
  getAllLuxuryCars(){
  this.carService.getLuxuryCars().subscribe(cars=>{
  this.luxuryCars = cars;
  });
  }

}
