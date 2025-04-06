import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FirstPageComponent} from './component/first-page/first-page.component';
import {LoginComponent} from "./component/login/login.component";
import {RegisterComponent} from "./component/register/register.component";
import {ClientComponent} from "./component/client/client.component";
import {AdminComponent} from "./component/admin/admin.component";
import {ClientAuthGuard} from "./ClientAuthGuard";
import {AdminAuthGuard} from "./AdminAuthGuard";

const routes: Routes = [
  {path: '', component: FirstPageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'first_page', component: FirstPageComponent},
  {path: 'client',
    component: ClientComponent,
    canActivate: [ClientAuthGuard]},
  {path: 'admin', component: AdminComponent, canActivate: [AdminAuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
