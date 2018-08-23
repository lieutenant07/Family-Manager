import { Component, OnInit } from '@angular/core';
import { FamilyService } from '../family.service';
import { Family } from '../Family';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { Router } from '@angular/router';



@Component({
  selector: 'create-family',
  templateUrl: './create-family.component.html',
  styleUrls: ['./create-family.component.css']
})
export class CreateFamilyComponent implements OnInit {
  family: Family = new Family();
  submitted = false;
  public familyForm: FormGroup;

  constructor(private familyService: FamilyService, private formBuilder: FormBuilder, private _route: Router) { }
  //Once family form object is created assign respective controls
  ngOnInit() {
    this.familyForm = this.formBuilder.group({
      father: this.formBuilder.group({
        firstName: [null, [Validators.required, Validators.maxLength(25)]],
        secondName: [null, [Validators.required, Validators.maxLength(25)]],
        pesel: [null, [Validators.required]],
        birthDate: [null, [Validators.required]]
      }),
      children: this.formBuilder.array([
        this.getChild()
      ])
    });
  }

  // convenience getters for easy access to form fields
  get f() { return this.familyForm.controls.father; }
  get c() { return this.familyForm.get('children') as FormArray; }

  //Separate child builder to make children array more readable
  private getChild() {
    return this.formBuilder.group({
      firstName: [null, [Validators.required, Validators.maxLength(25)]],
      secondName: [null, [Validators.required, Validators.maxLength(25)]],
      pesel: [null, [Validators.required]],
      sex: [null, [Validators.required]],
      birthDate: [null, [Validators.required]]
    });
  }

  //Add control to the family form's children array
  addChild() {
    const control = <FormArray>this.familyForm.controls['children'];
    control.push(this.getChild());
  }
  //Remove control from the family form's children array
  removeChild(i: number) {
    const control = <FormArray>this.familyForm.controls['children'];
    control.removeAt(i);
  }

  getFamilyId() {
    return this.family.id;
  }

  //Perform asynchronous operations, which are additionally nested in one another, subscribing to Observables (POST methods from the service layer)
  //1. Create family
  //2. Assign father to the created family
  //3. Assign children to the created family
  save() {
    this.familyService.createFamily()
      .subscribe(data => {
        const control = <FormArray>this.familyForm.controls['children'];
        for (let i = 0; i < control.length; i++) {
          this.family = data; this.familyService.addChildToFamily(this.family.id, (<FormArray>this.familyForm.get('children')).at(i).value)
            .subscribe(data => console.log(data), error => console.log(error));
        }
        this.family = data; this.familyService.addFatherToFamily(this.family.id, (<FormGroup>this.familyForm.get('father')).value)
          .subscribe(data => { console.log(data); this._route.navigate(['readFamily/' + this.family.id]); }, error => console.log(error));
      }
        , error => console.log(error));
  }

  //Handle 'submit' button event
  onSubmit() {
    this.submitted = true;

    if (this.familyForm.invalid) {
      return;
    }
    this.save();
  }
}
