import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CarService } from './shared/car/car.service';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { CarListComponent } from './home/car-list/car-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CarEditComponent } from './home/car-edit/car-edit.component';
import {RouterModule} from "@angular/router";
import {appRoutes} from "./Route";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    CarListComponent,
    CarEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [CarService],
  bootstrap: [AppComponent]
})
export class AppModule { }
