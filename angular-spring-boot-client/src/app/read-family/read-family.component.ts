import { Component, OnInit, Input } from '@angular/core';
import { FamilyService } from '../family.service';
import { Child } from '../Child';
import { Father } from '../Father';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-read-family',
  templateUrl: './read-family.component.html',
  styleUrls: ['./read-family.component.css']
})
export class ReadFamilyComponent implements OnInit {

  children: Child[]=[];
  father: Father = new Father();
  familyId: number;

  constructor(private familyService: FamilyService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    let familyId: number = this.activatedRoute.snapshot.params['familyId'];
    this.readFather(familyId);
    this.readChildren(familyId);
  }
  //Subscribe to observables returned by get request from the service layer,
  //in order to return requested data
  readFather(familyId:number){
    this.familyService.getFather(familyId).subscribe(data=>this.father=data);
  }
  readChildren(familyId:number){
    this.familyService.getChildren(familyId).subscribe(data=>{this.children=data;console.log(this.children)});
  }
}
