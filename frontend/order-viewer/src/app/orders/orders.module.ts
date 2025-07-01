import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderListComponent } from './order-list/order-list.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import { FilterPanelComponent } from './filter-panel/filter-panel.component';
import { StatisticsWidgetComponent } from './statistics-widget/statistics-widget.component';



@NgModule({
  declarations: [
    StatisticsWidgetComponent
  ],
  imports: [
    CommonModule
  ]
})
export class OrdersModule { }
