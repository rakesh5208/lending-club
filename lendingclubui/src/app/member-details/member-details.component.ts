import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MemberService } from '../member.service';
import { Member } from '../member';
import { UiStateService } from '../services/ui-state.service';


@Component({
  selector: 'app-member-details',
  templateUrl: './member-details.component.html',
  styleUrls: ['./member-details.component.scss']
})
export class MemberDetailsComponent implements OnInit {
  member: Member = null;
  id = '';

  constructor(private activatedRoute: ActivatedRoute,
    private memberService: MemberService,
    private uiState: UiStateService) {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      this.uiState.updateLoaderState(true);
      this.id = paramMap.get('id');
      this.memberService.getMember(this.id)
        .subscribe(member => { 
          this.member = member; 
          this.uiState.updateLoaderState(false);
        })
    });
  }

  ngOnInit() {

  }

}
