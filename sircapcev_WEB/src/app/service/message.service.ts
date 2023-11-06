import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
  export class MessageService<T> {
    private subject = new BehaviorSubject<T | null>(null);
  
    public sendMessage(data: T): void {
      this.subject.next(data);
    }
  
    public getMessage(): Observable<T | null> {
      return this.subject.asObservable();
    }
  constructor() { }
}
