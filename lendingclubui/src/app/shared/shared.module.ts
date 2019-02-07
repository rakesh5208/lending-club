import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http'
import { PaginatorComponent } from './paginator/paginator.component'
import { NotifyComponent } from './notify/notify.component';
import { SerachBoxComponent } from './serach-box/serach-box.component';
import { FilterBoxComponent } from './filter-box/filter-box.component';
import { LoaderComponent } from './loader/loader.component';

const components = [
  PaginatorComponent,
  NotifyComponent,
  SerachBoxComponent,
  FilterBoxComponent,
  LoaderComponent
];
@NgModule({
  declarations: [components],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
  exports: [components,
    FormsModule,
    HttpClientModule]
})
export class SharedModule { }
