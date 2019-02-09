import { Component, OnInit, OnDestroy } from '@angular/core';
import { MemberService } from '../member.service';
import { Member } from '../member';
import { UiStateService } from '../services/ui-state.service';
import { combineLatest } from 'rxjs';
import { Observable, Subject } from 'rxjs';
import { takeWhile, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.scss']
})
export class MemberListComponent implements OnInit, OnDestroy {


  cols = [{ label: '#id', key: 'id', type: 'number', sortable: false },
  { label: 'Loan Amount', key: 'loadAmnt', type: 'number', sortable: false },
  { label: 'Loan Status', key: 'loadStatus', sortable: false },
  { label: 'Last Payment Date', key: 'lastPymntDate', sortable: false },
  { label: 'Last Payment Amount', key: 'lastPymntAmnt', type: 'number', sortable: false }];

  sortableMeta = [];
  members: Member[] = [];
  filterOptions = [{
    key: 'home_ownership',
    label: 'Home Ownership',
    options: [
      { label: 'MORTGAGE', value: 'MORTGAGE' },
      { label: 'RENT', value: 'RENT' },
      { label: 'OWN', value: 'OWN' }
    ]
  },
  {
    key: 'verification_status',
    label: 'Verification Status',
    options: [
      { label: 'Verified', value: 'Verified' },
      { label: 'Source Verified', value: 'Source Verified' },
      { label: 'Not Verified', value: 'Not Verified' }
    ]
  },
  {
    key: 'loan_status',
    label: 'Loan Status',
    options: [
      { label: 'Late (31-120 days)', value: 'Late (31-120 days)' },
      { label: 'Late (16-30 days)', value: 'Late (16-30 days)' },
      { label: 'In Grace Period', value: 'In Grace Period' },
      { label: 'Fully Paid', value: 'Fully Paid' },
      { label: 'Default', value: 'Default' },
      { label: 'Charged Off', value: 'Charged Off' }
    ]
  }
  ];
  /**
   * paginator config
   */
  totalRecords = 0;
  paginationState = { currentPage: 0, limit: 0 }
  /**
   * selected filters
   */
  selectedFilters = [];
  subscriberLive = true;
  searchTerm = '';
  constructor(private memberService: MemberService, private uiState: UiStateService) {
    this.filterOptions.push(this.initGradeFilter());


    combineLatest(this.uiState.paginateState$, this.uiState.selectedFilters,this.uiState.searchTerm$)
    .pipe(takeWhile(()=>this.subscriberLive))
    .subscribe(([paginate, selectedFilter,searchTerm]) => {
      this.paginationState = paginate;
      this.selectedFilters = selectedFilter;
      this.searchTerm = searchTerm;
      this.getMembers();
    });
    
  }

  ngOnInit() {

  }

  onPageChange($event) {
    if ($event) {
      this.uiState.updatePaginatedState($event);
    }
  }
  /**
   * Get sort icon based on current sorting
   * @param key :object key
   */
  getSortIcon(key: string): string {
    const index = this.sortableMeta.findIndex(el => {
      return el.name === key
    })
    if (index == -1) {
      return 'fa-sort'
    }
    else {
      return this.sortableMeta[index].sortOrder === 1 ? 'fa-sort-asc' : 'fa-sort-desc';
    }
  }

  sort() {

  }
  initGradeFilter() {
    const options = [];
    for (let i = 0; i < 7; i++) {
      const charValue = String.fromCharCode(i + 65);
      options.push({ label: charValue, value: charValue });
    }
    return { key: 'grade', label: 'Grade', options: options };
  }

  onApply(event) {
    this.uiState.updatePaginatedState({ currentPage: 1, limit: this.paginationState.limit });
    this.uiState.updateSelectedFilter(event);
  }

  getMembers() {
    const { currentPage, limit } = this.paginationState;
    this.uiState.updateLoaderState(true);
    this.memberService.getMembers(currentPage, limit, this.selectedFilters,this.searchTerm).subscribe(paginatedMembers => {
      if (paginatedMembers) {
        this.totalRecords = paginatedMembers.totalRecords;
        this.members = paginatedMembers.contents;
      }
      this.uiState.updateLoaderState(false);
    });
  }

  ngOnDestroy(){
    this.subscriberLive = false;
  }
}
