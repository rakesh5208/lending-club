import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
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
  getMembers(currentPage:number,pageSize:number,cols:Array<{key:string,options:string[]}>,query?:string):Observable<MemberPaginated>{
    let path = cols.reduce((prev,curr)=>{
      prev += `&${curr.key}=` + curr.options.map((option:any)=> option.value).join('|')
      return prev;
    },'')
    if(query){
      path +=`&q=${query}`;
    }
    const url =`${this.baseUrl}/members?currentPage=${currentPage}&recordSize=${pageSize}${encodeURI(path)}`;
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
  searchMember(searchTerm:String):Observable<MemberPaginated>{
    return this.http.get<MemberPaginated>(encodeURI(`${this.baseUrl}/members/search?q=${searchTerm}`)).
    pipe(catchError(error=>{
      this.showError(error);
      return of(null)
    }));
  }


  

  private showError(error){
    let message = '';
    if(error instanceof HttpErrorResponse){
        switch(error.status){
          case 0:
          message = 'Error in connection with server!!';
          break;
          case 500:
            message = 'Internal Server Error occured !!';
          break;
          case 404:
            message= 'Resource not found !!'
            break;
          case 400:
            message=  'Bad Request !!';
          default:
            message =  error.error.error;
        }
    }else{
      message = error.message;  
    }
    this.notifyService.showNotfy({message:message,type:'DANGER'});
  }


}
