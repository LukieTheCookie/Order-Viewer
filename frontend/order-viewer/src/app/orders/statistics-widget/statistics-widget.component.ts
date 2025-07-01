import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { OrderService } from '../order.service'; 

@Component({
  selector: 'app-statistics-widget',
  templateUrl: './statistics-widget.component.html',
  styleUrls: ['./statistics-widget.component.scss']
})
export class StatisticsWidgetComponent implements OnChanges {
  @Input() filters: any = {};
  stats: any;

  constructor(private orderService: OrderService) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['filters']) {
      this.loadStats();
    }
  }

  loadStats() {
    this.orderService.getStatistics(this.filters).subscribe({
      next: (data) => this.stats = data,
      error: () => this.stats = null
    });
  }
}
