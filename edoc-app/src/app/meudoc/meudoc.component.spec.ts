import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeudocComponent } from './meudoc.component';

describe('MeudocComponent', () => {
  let component: MeudocComponent;
  let fixture: ComponentFixture<MeudocComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeudocComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeudocComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
