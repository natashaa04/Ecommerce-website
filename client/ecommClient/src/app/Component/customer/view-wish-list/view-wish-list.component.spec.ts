import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewWishListComponent } from './view-wish-list.component';

describe('ViewWishListComponent', () => {
  let component: ViewWishListComponent;
  let fixture: ComponentFixture<ViewWishListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewWishListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewWishListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
