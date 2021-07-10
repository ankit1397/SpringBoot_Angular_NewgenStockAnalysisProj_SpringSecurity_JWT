import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { DatePipe } from '@angular/common';
import * as _moment from 'moment';
import { HttpClient } from '@angular/common/http';
import { default as _rollupMoment } from 'moment';
import { TokenStorageService } from '../_services/token-storage.service';
import { Router } from '@angular/router';

const moment = _rollupMoment || _moment;
const API_URL = 'http://localhost:8080/api/getStockData';
export const MY_FORMATS = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'DD-MM-YYYY',
    monthYearLabel: 'YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY',
  },
};

@Component({
  selector: 'app-stock-data',
  templateUrl: './stock-data.component.html',
  styleUrls: ['./stock-data.component.css'],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
  ],
})
export class StockDataComponent implements OnInit {

  stockData: any;
  startDate = '';
  endDate = '';
  dataAvail = false;
  isLoggedIn = false;

  range = new FormGroup({
    start: new FormControl(moment()),
    end: new FormControl(moment())
  });

  padding: any = { left: 10, top: 5, right: 10, bottom: 5 };
  titlePadding: any = { left: 50, top: 0, right: 0, bottom: 10 };
  getWidth(): any {
    if (document.body.offsetWidth < 850) {
      return '100%';
    }

    return 850;
  }
  xAxis: any =
    {
      dataField: 'date',
      type: 'date',
      baseUnit: 'year',
      valuesOnTicks: true,
      minValue: this.startDate,
      maxValue: this.endDate,
      tickMarks: {
        visible: true,
        interval: 1,
        color: '#BCBCBC'
      },
      unitInterval: 1,
      gridLines: {
        visible: true,
        interval: 3,
        color: '#BCBCBC'
      },
      labels: {
        angle: -45,
        rotationPoint: 'topright',
        offset: { x: 0, y: -25 }
      }
    };
  valueAxis: any =
    {
      visible: true,
      title: { text: 'Price<br>' },
      tickMarks: { color: '#BCBCBC' }
    };
  seriesGroups: any =
    [
      {
        type: 'line',
        series: [
          { dataField: 'open', displayText: 'Open Price' },
          { dataField: 'close', displayText: 'Close Price' }
        ]
      }
    ];

  constructor(private datePipe: DatePipe, private http: HttpClient, private tokenStorageService: TokenStorageService, private router: Router) {
    console.log("Stock Data constructor called");
  }

  ngOnInit() {
    console.log("Stock Data onInit called");
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (!this.isLoggedIn) {
      this.router.navigate(['login']);
    }

  }

  dateValue() {
    this.range.value.start = this.datePipe.transform(this.range.value.start, 'dd-MM-yyyy');
    this.range.value.end = this.datePipe.transform(this.range.value.end, 'dd-MM-yyyy');
    console.log(this.range.value);
    this.http.post(API_URL, this.range.value).subscribe(
      data => {
        console.log(data);
        this.stockData = data;
        if (this.stockData.length > 0) {
          this.startDate = this.stockData[0].date;
          this.endDate = this.stockData[this.stockData.length - 1].date;
          console.log(this.stockData[0].date);
          console.log(this.stockData[this.stockData.length - 1].date);
          this.dataAvail = true;
        }
        else {
          alert('No data b/w input date Range!');
        }
      },
      err => {
        console.log(err.error.message);
        alert(err.error.message);
      }
    );
  }

}
