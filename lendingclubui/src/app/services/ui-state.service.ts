import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UiStateService {

  private _paginatedState = new BehaviorSubject<{ limit: number, currentPage: number }>({ limit: 10, currentPage: 1 });
  private _selectedFilterState = new BehaviorSubject<Array<{ key: string, label: string, options: [{ label: string, value: string }] }>>([]);
  private _searchTerm = new BehaviorSubject<string>('');
  private _loaderState = new BehaviorSubject<boolean>(false);
  
  /**
   * expose as observable
   */
  public paginateState$ = this._paginatedState.asObservable();
  public selectedFilters = this._selectedFilterState.asObservable();
  public searchTerm$ = this._searchTerm.asObservable();
  public loaderState$ = this._loaderState.asObservable();
  constructor() { }

  updatePaginatedState(state: { limit: number, currentPage: number }) {
    this._paginatedState.next(state);
  }

  updateSelectedFilter(filters:[{ key: string, label: string, options: [{ label: string, value: string }] }]){
    this._selectedFilterState.next(filters);
  }
  updateSearchTerm(searchTerm:string){
    this._searchTerm.next(searchTerm);
  }
  updateLoaderState(loading:boolean){
    this._loaderState.next(loading);
  }
}
