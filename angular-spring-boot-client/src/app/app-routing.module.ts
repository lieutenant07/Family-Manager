import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateFamilyComponent } from './create-family/create-family.component';
import { ReadFamilyComponent } from './read-family/read-family.component';
import { SearchFamilyComponent } from './search-family/search-family.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { HomeComponent } from './home/home.component';
 
const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'createFamily', component: CreateFamilyComponent },
    { path: 'read', component: ReadFamilyComponent },
    { path: 'readFamily/:familyId', component: ReadFamilyComponent },
    { path: 'searchFamily', component: SearchFamilyComponent },
    { path: 'searchResults/:queryString', component: SearchResultsComponent },
    { path: 'home', component: HomeComponent}
];
 
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
 
export class AppRoutingModule { }