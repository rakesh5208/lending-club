import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MemberService } from '../member.service';
import { Member } from '../member';


@Component({
  selector: 'app-member-details',
  templateUrl: './member-details.component.html',
  styleUrls: ['./member-details.component.scss']
})
export class MemberDetailsComponent implements OnInit {
  member:Member = null;
  id = '';
  
  constructor(private activatedRoute:ActivatedRoute,
    private memberService:MemberService) { 
    this.activatedRoute.paramMap.subscribe(paramMap=>{
      this.id = paramMap.get('id');
      this.memberService.getMember(this.id)
      .subscribe(member => this.member = member)
    });
  }

  ngOnInit() {

  }

}
