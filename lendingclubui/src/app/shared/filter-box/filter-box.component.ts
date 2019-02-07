import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations'

@Component({
  selector: 'app-filter-box',
  templateUrl: './filter-box.component.html',
  styleUrls: ['./filter-box.component.scss'],
  animations: [
    trigger('openClose', [
      state('open', style({
        'min-height': '200px',
        'box-shadow': '1px 1px 12px #2f2f67',
         'padding-bottom': '10px',
         'opacity':1
      })),
      state('close', style({
        'height': '0px',
        'opacity':0
      })),

      transition('close <=> open', [
        animate('150ms')
      ])])
  ]
})
export class FilterBoxComponent implements OnInit {
  @Input() filterState = 'close'
  @Output() filterStateChange: EventEmitter<string> = new EventEmitter<string>();
  _filterOptions: { key: string, label: string, options: { value: string, label: string, selected?: boolean }[] }[] = []
  set filterOptions(filterOptions){
    this._filterOptions = filterOptions;
    // this.markAllSelected();
  }
  @Input() get filterOptions(){
    return this._filterOptions;
  }
  @Output() apply = new EventEmitter<any>();
  constructor() { }

  ngOnInit() {
  }

  close() {
    this.filterState = 'close';
    this.filterStateChange.emit('close');
  }

  emitSelection() {
    const selectedFilter = []
    this.filterOptions.reduce((foPrev, foCurr) => {
      const selecteOptons = foCurr.options.reduce((opPrev, opCurr) => {
        return opCurr.selected ? [...opPrev, opCurr] : [...opPrev];
      }, []);
      if (selecteOptons.length > 0) {
        foPrev.push({ key: foCurr.key, label: foCurr.label, options: selecteOptons });
      }
      return foPrev;
    }, selectedFilter)
    this.close();
    this.apply.emit(selectedFilter);
  }

  reset(){
    this.filterOptions.map((fo)=>{
      fo.options.map(o=>o.selected = false);
    });
  }
  markAllSelected(){
    this.filterOptions.map(fo=>{
      fo.options.map(o=>o.selected = true);
    });
  }
}
