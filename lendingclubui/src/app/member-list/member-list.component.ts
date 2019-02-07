import { Component, OnInit } from '@angular/core';
import { MemberService } from '../member.service';
import { Member } from '../member';

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.scss']
})
export class MemberListComponent implements OnInit {


  cols = [{ label: '#id', key: 'id', type: 'number', sortable: true },
  { label: 'Loan Amount', key: 'loadAmnt', type: 'number', sortable: true },
  { label: 'Loan Status', key: 'loadStatus', sortable: true },
  { label: 'Last Payment Date', key: 'lastPymntDate', sortable: true },
  { label: 'Last Payment Amount', key: 'lastPymntAmnt', type: 'number', sortable: true }];

  sortableMeta = [];
  members: Member[] = [];
  totalRecords = 0;
  currentPage = 1;
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
      { label: 'Current', value: 'Charged Off' }
    ]
  }
  ];
  loading = false;
  constructor(private memberService: MemberService) {
    this.filterOptions.push(this.initGradeFilter());
  }

  ngOnInit() {
    this.getMembers(this.currentPage, 10);
  }

  onPageChange($event) {
    if ($event) {
      const { currentPage, limit } = $event;
      this.getMembers(currentPage,limit);
      
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
    this.loading = true;
    setTimeout(()=>this.loading = false, 3000);
  }

  getMembers(currentPage,limit){
    this.loading = true;
    this.memberService.getMembers(currentPage, limit).subscribe(paginatedMembers => {
      if (paginatedMembers) {
        this.totalRecords = paginatedMembers.totalRecords;
        this.members = paginatedMembers.contents;
      }
      setTimeout(()=> this.loading = false,1);
    });    
  }
}
