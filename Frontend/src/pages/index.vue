<template>
  <q-page padding>
      <body class="text-h5">
        Hallo Admin Semoga kerjamu Untuk bagian Pengatur Hak Akses Dapat berjalan sebagai mana mestinya
      </body><br>
      <body class="text-h5">
        <b>Universitas Bandar Lampung </b><br>
        Contact : 0896xxxxxxxx
      </body>
      <div class="q-pa-md">
      <q-carousel
        arrows
        animated
        v-model="slide"
        height="400px"
      >
        <q-carousel-slide name="first" img-src="https://cdn.quasar.dev/img/mountains.jpg">
          <div class="absolute-bottom custom-caption">
            <div class="text-h2">Academic</div>
            <div class="text-subtitle1">Mountains</div>
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="second" img-src="https://cdn.quasar.dev/img/parallax1.jpg">
          <div class="absolute-bottom custom-caption">
            <div class="text-h2">Finance</div>
            <div class="text-subtitle1">Famous City</div>
          </div>
        </q-carousel-slide>
        <q-carousel-slide name="third" img-src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom custom-caption">
            <div class="text-h2">Third stop</div>
            <div class="text-subtitle1">Dream</div>
          </div>
        </q-carousel-slide>
      </q-carousel>
    </div>
  </q-page>
</template>

<style lang="sass" scoped>
.custom-caption
  text-align: center
  padding: 12px
  color: white
  background-color: rgba(0, 0, 0, .3)
</style>

<script>
export default {
  data () {
    return {
      slide: 'first'
    }
  }
}
</script>
