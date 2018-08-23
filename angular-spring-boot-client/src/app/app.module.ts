import { CreateFamilyComponent } from './create-family/create-family.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
 
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import { ReadFamilyComponent } from './read-family/read-family.component';
import { SearchFamilyComponent } from './search-family/search-family.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { HomeComponent } from './home/home.component';
import { DatePipe } from '@angular/common';


 
@NgModule({
  declarations: [
    AppComponent,
    CreateFamilyComponent,
    ReadFamilyComponent,
    SearchFamilyComponent,
    SearchResultsComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BsDatepickerModule.forRoot()
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }