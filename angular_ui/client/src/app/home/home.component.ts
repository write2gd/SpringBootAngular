import {Component, OnInit} from '@angular/core';
import {OktaAuthService} from '@okta/okta-angular';
import {Router} from '@angular/router';
import {UserService} from '../shared/user/user.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    isAuthenticated: boolean;
    userClaims: any;

    constructor(private oktaAuth: OktaAuthService, private router: Router) {
    }

    async ngOnInit() {
        this.isAuthenticated = await this.oktaAuth.isAuthenticated();
        // Subscribe to authentication state changes
        this.oktaAuth.$authenticationState.subscribe(
            (isAuthenticated: boolean) => this.isAuthenticated = isAuthenticated
        );
    }

    Logout() {
        localStorage.removeItem('userToken');
        this.router.navigate(['/login']);
    }
}
