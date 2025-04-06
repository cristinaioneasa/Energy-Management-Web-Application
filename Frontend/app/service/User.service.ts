import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../model/User";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class UserService{
  // 8080 pentru local ; 8083 pentru docker
  baseURLAuth: string = "http://localhost:8083/userAuth";
  baseURL: string = "http://localhost:8083/user"

  //baseURLAuth: string = "http://localhost:8080/userAuth";
  //baseURL: string = "http://localhost:8080/user"

  userInfo: any;
  userName: String | undefined;
  userEmail: String | undefined;

  constructor(private httpClient: HttpClient) {
  }

  register(user: User): Observable<User>{
    return this.httpClient.post<User>(this.baseURLAuth + '/register', user);
  }

  loginUser(user: User):Observable<object> {
    //console.log(user);
    return this.httpClient.post(this.baseURLAuth + '/authenticate', user);
  }

  findAllUsers(token:string){
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User[]>(this.baseURL + "/findAll", {headers});
  }

  insertUser(username: string | undefined){
    return this.httpClient.post("http://localhost:8083/user/insertUser?username="+username, null);
  }

  deleteUser(token: string, id: number){
    console.log(this.baseURL + "/deleteUser/" + id)
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`)
    return this.httpClient.delete("http://localhost:8083/user/deleteUser/" + id, {headers});
  }
}
