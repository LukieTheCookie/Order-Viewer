import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Order {
  id: number;
  customerName: string;
  status: string;
  total: number;
  paid: boolean;
  createdDate: string;
  orderItemList?: OrderItem[];
}

export interface OrderItem {
  id: number;
  productName: string;
  quantity: number;
  price: number;
}

export interface OrderStatistics {
  count: number;
  grandTotal: number;
}

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = '/api/orders';

  constructor(private http: HttpClient) {}

  getOrders(filters: any = {}): Observable<Order[]> {
    let params = new HttpParams();
    Object.keys(filters).forEach(key => {
      if (filters[key] !== null && filters[key] !== undefined && filters[key] !== '') {
        params = params.set(key, filters[key]);
      }
    });
    return this.http.get<Order[]>(this.baseUrl, { params });
  }

  getOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.baseUrl}/${id}`);
  }

  markAsPaid(id: number): Observable<void> {
    return this.http.patch<void>(`${this.baseUrl}/${id}/pay`, {});
  }

  getStatistics(filters: any = {}): Observable<OrderStatistics> {
    let params = new HttpParams();
    Object.keys(filters).forEach(key => {
      if (filters[key] !== null && filters[key] !== undefined && filters[key] !== '') {
        params = params.set(key, filters[key]);
      }
    });
    return this.http.get<OrderStatistics>(`${this.baseUrl}/statistics`, { params });
  }
}