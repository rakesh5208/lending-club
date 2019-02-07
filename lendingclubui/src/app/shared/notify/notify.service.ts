import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

interface NotifyMeta{
  message:string,
  type: 'DANGER' | 'SUCCESS' 
}
@Injectable({
  providedIn: 'root'
})
export class NotifyService {

  private _notify = new BehaviorSubject<NotifyMeta>(null);
  private _notifyState = new BehaviorSubject<string>('close');

  notify$ = this._notify.asObservable();
  notifyState = this._notifyState.asObservable();
  constructor() { }
  showNotfy(notify:NotifyMeta){
    this._notify.next(notify);
    this._notifyState.next('open');
    setTimeout(this.close.bind(this),5000);
  }

  close(){
    this._notifyState.next('close');
  }
}
