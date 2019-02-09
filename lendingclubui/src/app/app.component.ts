import { Component, OnDestroy } from '@angular/core';
import { MemberService } from './member.service';
import { UiStateService } from './services/ui-state.service';
import { takeWhile } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnDestroy {
  title = 'lendingclupui';;
  loading = false;
  subscriberLive = true;
  constructor(private uiState: UiStateService) {
    this.uiState.loaderState$.pipe(takeWhile(() => this.subscriberLive)).subscribe(loading => {
      console.log('loading'+this.loading);
      this.loading =loading;
    })
  }
  search(serachTerm: string) {
    this.uiState.updateSearchTerm(serachTerm);
  }

  ngOnDestroy() {
    this.subscriberLive = false;
  }
}
