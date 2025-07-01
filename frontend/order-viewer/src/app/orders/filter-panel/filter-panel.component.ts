import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-filter-panel',
  templateUrl: './filter-panel.component.html',
  styleUrls: ['./filter-panel.component.scss']
})
export class FilterPanelComponent {
  @Output() filtersChanged = new EventEmitter<any>();

  filterForm: FormGroup;

  statuses = [
    { label: 'Pending', value: 'PENDING' },
    { label: 'Processing', value: 'PROCESSING' },
    { label: 'Shipped', value: 'SHIPPED' },
    { label: 'Cancelled', value: 'CANCELLED' },
  ];

  constructor(private fb: FormBuilder) {
    this.filterForm = this.fb.group({
      status: [''],
      createdFrom: [''],
      createdTo: [''],
      totalMin: [''],
      totalMax: ['']
    });

    this.filterForm.valueChanges.subscribe(val => {
      const transformed = {
        ...val,
      createdFrom: val.createdFrom ? val.createdFrom + 'T00:00:00' : undefined,
      createdTo: val.createdTo ? val.createdTo + 'T23:59:59' : undefined
    };

    this.filtersChanged.emit(transformed);
    });
  }

  resetFilters() {
    this.filterForm.reset();
  }
}
