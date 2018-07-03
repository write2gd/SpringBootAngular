import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CarService {
public API = '//localhost:8090/spring-boot';
  public CAR_API = this.API + '/api/cars';
  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.CAR_API);
  }

  getLuxuryCars(): Observable<any> {
    return this.http.get(this.CAR_API +'/luxury');
  }
  get(id: string) {
    return this.http.get(this.CAR_API + '/' + id);
  }

  save(car: any): Observable<any> {
    let result: Observable<Object>;
      if (car['id']) {
        result = this.http.put(this.CAR_API, car);
      } else {
        car.id=null;
        result = this.http.post(this.CAR_API, car);
      }
  return result;
  }

  remove(id: string) {
  return this.http.delete(id);
  }

}
