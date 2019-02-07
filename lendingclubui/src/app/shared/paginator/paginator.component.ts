import { Component, OnInit, Input, Output, OnChanges, SimpleChanges } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.scss']
})
export class PaginatorComponent implements OnInit {

  _totalRecords = 0;

  @Input() limit = 10;
  @Input() currentPage = 1;
  lastPage = 0;
  limits = [10,50,100,500];
  @Output() change = new EventEmitter();
  isFirst = true;

  @Input() get totalRecords(){
    return this._totalRecords;
  }  
  set totalRecords(totalRecords){
    this._totalRecords = totalRecords;
    this.lastPage = this.getLastPageCount();
    this.updatePaginator();
  }
  constructor() { }

  ngOnInit() {
   
  }

  first() {
    if (this.currentPage > 1) {
    this.currentPage = 1;
    this.updatePaginator();
    }
  }
  last() {
    if(this.currentPage <= this.lastPage){
    this.currentPage = this.lastPage;
    this.updatePaginator();
    }

  }
  next() {
    if(this.currentPage <= this.lastPage){
     this.currentPage++;
      this.updatePaginator();
    }
  }
  back() {
    if (this.currentPage > 1) {
      this.currentPage --;
      this.updatePaginator();
    }
  }
  getLastPageCount() {
    let lastPageCount = 0;
    const start = Math.floor(this._totalRecords / this.limit);
    if (this._totalRecords % this.limit == 0) {
      lastPageCount = start;
    } else {
      lastPageCount = start + 1;
    }
    return lastPageCount;
  }

  limitChange(event){
    event.stopImmediatePropagation();
    this.lastPage = this.getLastPageCount();
    this.currentPage = 1;
    this.updatePaginator();
  }

  updatePaginator(){
    this.change.emit({limit:this.limit, currentPage:this.currentPage})
  }
}
