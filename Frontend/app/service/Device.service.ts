import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Device} from "../model/Device";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  baseURL: string = "http://localhost:8084/device"; //8084 pentru docker
  //baseURL: string = "http://localhost:8081/device" //8081 pentru local

  constructor(private httpClient: HttpClient) {}

  findAllDevices() {
    return this.httpClient.get<Device[]>("http://localhost:8084/device/findAll");
  }

  insertDevice(device: Device):Observable<Device>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      })
    };
    return this.httpClient.post<Device>(this.baseURL + "/insertDevice", device, httpOptions);
  }

  deleteDevice(id: any){
    return this.httpClient.delete<Device>(this.baseURL + "/deleteDevice?id=" + id)
  }

  showDevices(idUser: any){
    return this.httpClient.get<Device[]>("http://localhost:8084/device/findByClient?id_client="+idUser)
  }

}
