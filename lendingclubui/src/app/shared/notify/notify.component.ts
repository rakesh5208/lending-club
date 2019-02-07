import { Component, OnInit } from '@angular/core';
import {trigger, state, style, transition, animate} from '@angular/animations'
import { NotifyService } from './notify.service';

@Component({
  selector: 'app-notify',
  template: `
  <div [@openClose]="notifyOpenState" class="notify-container">
    <div *ngIf="notify" class="alert alert-{{notify.type | lowercase}}">
        <div class="notify-message">
          <i class="fa fa-info">&nbsp;&nbsp;</i><span>{{notify.message}}</span>
        </div>
        <div class="notify-close">
          <span class="ui-clickable fa fa-times text-right" (click)="close()">&nbsp;&nbsp;</span></div>
          </div>
  </div>`,
  styles: [
    `.notify-container{
        z-index: 99999;
        position: fixed;
        width: 50%;
        text-align: center;
        top: 50px;
    }
    .notify-message{
      display:inline-block;
      width:90%;
    }
    .notify-close{
      display:inline-block;
      width:10%;
      text-align: right;
    }
    .alert{
      box-shadow: 1px 1px 12px;
    }`
  ],
  animations:[
    trigger('openClose',[
      state('open', style({
      'margin-left':'50%'

    })),
    state('close', style({
      'margin-left':'100%'
    })),
    
    transition('close <=> open', [
      animate('150ms')
    ])])
  ]
})
export class NotifyComponent implements OnInit {
  notify = null;
  notifyOpenState ='close';
  constructor(private notifyService:NotifyService) { }

  ngOnInit() {
    this.notifyService.notify$.subscribe(notify=>  this.notify = notify );
    this.notifyService.notifyState.subscribe(state=>this.notifyOpenState = state);
  }
  close(){
    this.notifyService.close();
  }
}
