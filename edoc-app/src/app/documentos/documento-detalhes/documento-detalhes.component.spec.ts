import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentoDetalhesComponent } from './documento-detalhes.component';

describe('DocumentoDetalhesComponent', () => {
  let component: DocumentoDetalhesComponent;
  let fixture: ComponentFixture<DocumentoDetalhesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentoDetalhesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentoDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
