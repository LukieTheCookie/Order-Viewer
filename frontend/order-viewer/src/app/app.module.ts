import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderListComponent } from './orders/order-list/order-list.component';
import { OrderDetailComponent } from './orders/order-detail/order-detail.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FilterPanelComponent } from './orders/filter-panel/filter-panel.component';

@NgModule({
  declarations: [
    AppComponent,
    OrderListComponent,
    OrderDetailComponent,
    FilterPanelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
