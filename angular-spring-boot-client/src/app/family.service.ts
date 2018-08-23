import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Family } from './Family';
import { Child } from './Child';
import { Father } from './Father';
import { AbstractControl } from '@angular/forms';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class FamilyService {

  private baseUrl = 'http://192.168.99.100:8080/fam';
  private searchUrl = 'http://192.168.99.100:8080/searchChild';
 
  constructor(private http: HttpClient,private datepipe: DatePipe) { }
 
  //Series of GET/POST request sent to spring-boot REST API's which return Observables in return. 
  //Service methods are utilized within the components, and subscription to Observables happens from there
  getFamily(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createFamily():Observable<Family>{
    return this.http.post<Family>(`${this.baseUrl}`,new Object());
  }

  addChildToFamily(familyId:number, ac: AbstractControl) {
    return this.http.post(`${this.baseUrl}/${familyId}/chld`,ac);
  }

  addFatherToFamily(familyId:number, ac: AbstractControl) {
    return this.http.post(`${this.baseUrl}/${familyId}/fath`,ac);
  }

  getFather(familyId:number):Observable<Father> {
    return this.http.get<Father>(`${this.baseUrl}/${familyId}/fath`);
  }

  getChildren(familyId:number):Observable<Child[]> {
    return this.http.get<Child[]>(`${this.baseUrl}/${familyId}/chld`);
  }

  searchCriteria(childFirstName: string, childSecondName: string, childPesel: string, childBirthDate: Date, childGender: string){
    let queryString: string=`?`;
    
    if(childFirstName != null){
      queryString+=`firstName=`+childFirstName+'&';
    }
    if(childSecondName != null){
      queryString+=`secondName=`+childSecondName+'&';
    }
    if(childPesel != null){
      queryString+=`pesel=`+childPesel+'&';
    }
    if(childBirthDate != null){
      //We use datepipe to convert timestamp (of type Date) to date string valid for the endpoint
      queryString+=`birthDate=`+this.datepipe.transform(childBirthDate,'yyyy-MM-dd')+'&';
    }
    if(childGender != null){
      queryString+=`sex=`+childGender+'&';
    }

    //As number of predicate vary, once concatenated we remove extra '&' from the end
    if(queryString.charAt(queryString.length-1)===`&`){
      return queryString.slice(0,-1);
    } else {
      return queryString;
    }
  }

  // fetchChildren(childFirstName: string, childSecondName: string, childPesel: string, childBirthDate: Date, childGender: string){
  //   let queryString: string = this.searchCriteria(childFirstName,childSecondName,childPesel,childBirthDate,childGender);
  //   return this.http.get(`${this.searchUrl}`+queryString);
  // }

  //Get fathers request sent to REST API, to perform database search for fathers, given the children characteristics
  fetchFathers(queryString: string, page: number){
    return this.http.get(`${this.searchUrl}`+queryString+`&page=`+page);
  }
}
