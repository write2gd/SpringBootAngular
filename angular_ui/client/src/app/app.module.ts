import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CarService} from './shared/car/car.service';
import {MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppComponent} from './app.component';
import {CarListComponent} from './home/car-list/car-list.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {CarEditComponent} from './home/car-edit/car-edit.component';
import {RouterModule} from "@angular/router";
import {appRoutes} from "./Route";
import {FormsModule} from "@angular/forms";
import {OktaAuthModule} from '@okta/okta-angular';
import {AuthInterceptor} from "./shared/security/auth.interceptor";
import {HomeComponent} from './home/home.component';

const config = {
    issuer: 'https://dev-210340.oktapreview.com/oauth2/gd',
    redirectUri: 'http://localhost:4200/client/implicit/callback',
    clientId: '0oafjvvexw3hsmNo30h7'
};

@NgModule({
    declarations: [
        AppComponent,
        CarListComponent,
        CarEditComponent,
        HomeComponent
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
        OktaAuthModule.initAuth(config),
        RouterModule.forRoot(appRoutes)
    ],
    providers: [CarService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
    bootstrap: [AppComponent]
})
export class AppModule {
}
