import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WindowRefService {

  constructor() { }
  get  nativeWindow():Window{
    return window;
  }
}
