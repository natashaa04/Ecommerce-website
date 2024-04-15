import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCatagoryComponent } from './post-catagory.component';

describe('PostCatagoryComponent', () => {
  let component: PostCatagoryComponent;
  let fixture: ComponentFixture<PostCatagoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCatagoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PostCatagoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
