import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'images'
})
export class ImagesPipe implements PipeTransform {

  transform(value: string | undefined | null, ...args: unknown[]): unknown {
    return '/assets/images/' + (value ? value : 'default.jpg');
  }

}
