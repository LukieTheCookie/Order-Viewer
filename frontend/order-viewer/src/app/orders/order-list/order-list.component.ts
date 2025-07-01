import { Component, OnInit } from '@angular/core';
import { Order, OrderService } from '../order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.scss']
})
export class OrderListComponent implements OnInit {
  orders: Order[] = [];
  filters: any = {};

  constructor(private orderService: OrderService, private router: Router) {}

  ngOnInit() {
    this.loadOrders();
  }

  onFiltersChanged(filters: any) {
    this.filters = filters;
    this.loadOrders();
  }

  loadOrders() {
    this.orderService.getOrders(this.filters).subscribe(data => {
      this.orders = data;
    });
  }

  selectOrder(order: Order) {
    this.router.navigate(['/orders', order.id]);
  }
}
