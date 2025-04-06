import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ChartService {

  private baseUrl = 'http://localhost:8087/sensor'; //docker
  //private baseUrl = 'http://localhost:8085/sensor';
  private existingChart: any;

  constructor(private httpClient: HttpClient) {}

  getChartByClient(idClient: number, timestamp: number): Observable<any> {
    // http://localhost:8085/sensor/daily?timestamp=1702329204850&idClient=63
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      })
    };
    return this.httpClient.get<any>(this.baseUrl+ "/daily?timestamp=" + timestamp + "&idClient=" + idClient, httpOptions);
  }

  // Get the existing Chart instance
  getExistingChart(): any {
    return this.existingChart;
  }

  // Set the existing Chart instance
  setExistingChart(chart: any): void {
    this.existingChart = chart;
  }
}
