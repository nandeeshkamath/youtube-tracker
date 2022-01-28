# YOUTUBE TRACKER

### Description

An app that queries YouTube's search API based on keywords and filters provided and sends video links to specified telegram channel.

#### Features:

- Can query based on multiple search keywords.
- Can filter search by video type.
- Can control the search time period. `<From>` to `<Now>` where `<From>` is number of hours from current time.
- Can search multiple YouTube channels recursively by one API call.
- Can control the target Telegram Channel.

### Inspiration

The app is a pet project of mine to keep myself updated with all new trailers of movies and series.
And possible extend the flexibility, so it could be used for other purposes too.

### Live Deployments

#### Workflow # 1 - Trailer tracker

- The app is deployed at [Railway](https://railway.app/).
- API calls are triggered by [GitHub Action workflow](/.github/workflows/trailer-tracker-cron.yml) regularly by a cron job.
Cron job runs once for every 4 hours.
- Videos matching keyword are sent to telegram channel [@trailertracker](https://t.me/trailertracker).

<i>More workflows coming soon</i>

### References

- [YouTube Search API](https://developers.google.com/youtube/v3/docs/search/list)
- [Telegram Send Message API](https://core.telegram.org/bots/api#sendmessage)