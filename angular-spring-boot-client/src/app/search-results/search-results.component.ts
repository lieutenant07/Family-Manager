import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FamilyService } from '../family.service';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  private page: number = 0;
  private pages: Array<number>;
  private fathers: Array<any>;
  private queryString: string = this.activatedRoute.snapshot.params['queryString'];

  constructor(private familyService: FamilyService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.getFathers();
  }

  //Method to allow pagination of pageable JSON fetched from REST API
  setPage(i, event: any) {
    event.preventDefault();//add this to change pages properly, 
    //(cancels default behaviour of redirecting to home page once button is clicked)
    this.page = i;
    this.getFathers();
  }

  getFathers() {
    this.familyService.fetchFathers(this.queryString, this.page).subscribe(data => {
      console.log(data);
      this.fathers = data['content'];
      this.pages = new Array(data['totalPages'])
    }
      , error => console.log(error));
  }

}
