import { Component, OnInit } from '@angular/core';
import { FamilyService } from '../family.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-family',
  templateUrl: './search-family.component.html',
  styleUrls: ['./search-family.component.css']
})
export class SearchFamilyComponent implements OnInit {

  public searchForm: FormGroup;
  submitted = false;

  constructor(private familyService: FamilyService, private formBuilder: FormBuilder, private _route: Router) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      firstName: [null, Validators.required],
      secondName: [null, Validators.required],
      pesel: [null, Validators.required],
      birthDate: [null, Validators.required],
      sex: [null, Validators.required]
    });
  }

  //Validity of the form should be checked before performing search.
  //Serch will be initiated only if at least one field from the form is populated
  isValid() {
    if (this.searchForm.get('firstName').valid ||
      this.searchForm.get('secondName').valid ||
      this.searchForm.get('pesel').valid ||
      this.searchForm.get('birthDate').valid ||
      this.searchForm.get('sex').valid) {
      return true;
    } else {
      return false;
    }
  }

  //Fetches appropriate queryString to match exposed REST API , using family service.
  getQueryString() {
    return this.familyService.searchCriteria(this.searchForm.get('firstName').value,
      this.searchForm.get('secondName').value,
      this.searchForm.get('pesel').value,
      this.searchForm.get('birthDate').value,
      this.searchForm.get('sex').value);
  }

  //Use the routes in order to redirect to SearchResultsComponent and save the queryString as parameter
  //, to fetch the search result data from there
  onSubmit() {
    this.submitted = true;
    this._route.navigate(['searchResults/' + this.getQueryString()]);
  }
}
