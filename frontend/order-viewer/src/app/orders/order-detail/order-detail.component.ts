import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order, OrderService } from '../order.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.scss']
})
export class OrderDetailComponent implements OnInit {
  order?: Order;
  loading = true;
  error = '';

  constructor(private route: ActivatedRoute, private orderService: OrderService) {}

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.orderService.getOrderById(id).subscribe({
      next: order => {
        this.order = order;
        this.loading = false;
      },
      error: () => {
        this.error = 'Order not found.';
        this.loading = false;
      }
    });
  }
}
