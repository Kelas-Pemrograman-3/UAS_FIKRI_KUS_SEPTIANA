<template>
  <div class="q-pa-md">
    <q-btn-dropdown color="primary" label="Dropdown Button">
      <q-list>
        <q-item clickable v-close-popup @click="onItemClick">
          <q-item-section>
            <q-item-label>Photos</q-item-label>
          </q-item-section>
        </q-item>

        <q-item clickable v-close-popup @click="onItemClick">
          <q-item-section>
            <q-item-label>Videos</q-item-label>
          </q-item-section>
        </q-item>

        <q-item clickable v-close-popup @click="onItemClick">
          <q-item-section>
            <q-item-label>Articles</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </q-btn-dropdown>
  </div>
</template>

<script>
export default {
  data () {
    return {
      kodeMataKuliah: '',
      namamatakuliah: '',
      jam: '',
      hari: '',
      ruangan: ''
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('matakuliah/postmk', {
        kodeMataKuliah: this.kodeMataKuliah,
        namamatakuliah: this.namamatakuliah,
        jam: this.jam,
        hari: this.hari,
        ruangan: this.ruangan
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.showNotif(res.data.pesan, 'Positive')
          this.onReset()
        }
      })
    },
    onReset () {
      this.kodeMataKuliah = ''
      this.namamatakuliah = ''
      this.jam = ''
      this.hari = ''
      this.ruangan = ''
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: color
      })
    }
  }
}
</script>
