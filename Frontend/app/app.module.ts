import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FirstPageComponent} from './component/first-page/first-page.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatListModule} from '@angular/material/list';
import {LoginComponent} from "./component/login/login.component";
import {RegisterComponent} from "./component/register/register.component";
import {MatIconModule} from "@angular/material/icon";
import {ClientComponent} from "./component/client/client.component";
import {AdminComponent} from "./component/admin/admin.component";
import {ClientAuthGuard} from "./ClientAuthGuard";
import {AdminAuthGuard} from "./AdminAuthGuard";
import {WebSocketService} from "./service/WebSocketService";
import { ChatComponent } from './component/chat/chat.component';
import {MatSnackBar} from "@angular/material/snack-bar";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';


@NgModule({
  declarations: [
    AppComponent,
    FirstPageComponent,
    LoginComponent,
    RegisterComponent,
    ClientComponent,
    AdminComponent,
    ChatComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatListModule,
    FormsModule,
    AppRoutingModule,
    MatIconModule,
    BrowserAnimationsModule,
    MatSnackBarModule
  ],
  providers: [ClientAuthGuard, AdminAuthGuard, WebSocketService, MatSnackBar],
  bootstrap: [AppComponent]
})
export class AppModule {
}
