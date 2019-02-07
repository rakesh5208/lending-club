import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable,of, observable } from 'rxjs';
import {catchError,} from 'rxjs/operators'
import { MemberPaginated, Member } from './member';
import { NotifyService } from './shared/notify/notify.service';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private _globalData = window['lendingClubGlobalData'];
  private baseUrl = '';
  constructor(private http:HttpClient,
    private notifyService:NotifyService) { 
    this.baseUrl = this._globalData['apiBaseEndPoint'];
    
  }

  getMembers(currentPage:number,pageSize:number):Observable<MemberPaginated>{
    const url =`${this.baseUrl}/members/?currentPage=${currentPage}&recordSize=${pageSize}`;
    return this.http.get<MemberPaginated>(url).pipe(catchError(error=>{
      this.showError(error);
      return of(null)
    }));;
  }

  getMember(id:string):Observable<Member>{
    return this.http.get<Member>(`${this.baseUrl}/members/${id}`).
    pipe(catchError(error=>{
      this.showError(error);
      return of(null)
    }));
  }
  

  private showError(error){
    if(error.error && error.error.error){
      this.notifyService.showNotfy({message:error.error.error,type:'DANGER'});
    }else{
      this.notifyService.showNotfy({message:error.message || error ,type:'DANGER'});
    }
  }
}
