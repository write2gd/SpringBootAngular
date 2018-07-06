import {Routes} from '@angular/router'
import {CarListComponent} from "./home/car-list/car-list.component";
import {CarEditComponent} from "./home/car-edit/car-edit.component";
import {OktaCallbackComponent, OktaAuthModule} from '@okta/okta-angular';
import {HomeComponent} from "./home/home.component";

export const appRoutes: Routes = [
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {
        path: 'home',
        component: HomeComponent
    },
    {path: '', redirectTo: '/car-list', pathMatch: 'full'},
    {
        path: 'car-list',
        component: CarListComponent
    },
    {
        path: 'car-add',
        component: CarEditComponent
    },
    {
        path: 'car-edit/:id',
        component: CarEditComponent
    },
    {
        path: 'implicit/callback',
        component: OktaCallbackComponent
    }
];
