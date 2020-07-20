import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentoListaComponent } from './documento-lista.component';

describe('DocumentoListaComponent', () => {
  let component: DocumentoListaComponent;
  let fixture: ComponentFixture<DocumentoListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentoListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentoListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
