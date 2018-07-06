import { Routes } from '@angular/router'
import {CarListComponent} from "./home/car-list/car-list.component";
import {CarEditComponent} from "./home/car-edit/car-edit.component";

export const appRoutes: Routes = [
  { path: '', redirectTo: '/car-list', pathMatch: 'full' },
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
  }
];
