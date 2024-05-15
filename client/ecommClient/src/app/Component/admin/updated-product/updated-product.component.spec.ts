import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatedProductComponent } from './updated-product.component';

describe('UpdatedProductComponent', () => {
  let component: UpdatedProductComponent;
  let fixture: ComponentFixture<UpdatedProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdatedProductComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdatedProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
