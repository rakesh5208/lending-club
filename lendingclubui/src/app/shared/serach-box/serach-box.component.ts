import { Component, OnInit, Output, EventEmitter, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { distinctUntilChanged, debounceTime, takeWhile } from 'rxjs/operators';
@Component({
  selector: 'app-serach-box',
  template: `<div class="search-box" > 
                <input class="form-control" placeholder="Serach here !!" type="text" (keyup)="_subject.next($event.target.value)" />
                <i class="fa fa-search"></i>
              </div>`,
  styles: [
    `
    .search-box{
      margin-top:-8px;
    }
    .fa-search{
      margin-top: -27px;
      display: block;
      margin-right: 10px;
     }
     .form-control{
       width:90%;
       display: inline-block;
     }`
  ]
})
export class SerachBoxComponent implements OnInit , OnDestroy{

  
  searchBoxState='close';
  searchText = '';
  @Output() enter = new EventEmitter();
  _subject = new Subject();
  alive = true;
  constructor() {
    this._subject.pipe(takeWhile( ()=>this.alive),distinctUntilChanged(),debounceTime(500)).subscribe(text=>{
      this.enter.emit(text);
    })
   }

  ngOnInit() {
    
  }

  ngOnDestroy(){
    this.alive = false;
  }
}


