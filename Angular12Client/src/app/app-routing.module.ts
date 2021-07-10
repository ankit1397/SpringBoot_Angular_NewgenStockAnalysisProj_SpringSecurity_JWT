import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { StockDataComponent } from './stock-data/stock-data.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'stockData', component: StockDataComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
