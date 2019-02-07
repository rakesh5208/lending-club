import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-serach-box',
  template: `<div class="search-box" > 
                <input class="form-control" placeholder="Serach here !!" type="text" />
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
export class SerachBoxComponent implements OnInit {

  constructor() { }
  searchBoxState='close'
  ngOnInit() {
    
  }

}


