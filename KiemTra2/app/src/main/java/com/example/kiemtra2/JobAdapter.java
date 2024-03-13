package com.example.kiemtra2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiemtra2.databinding.ItemJobBinding;

import java.text.SimpleDateFormat;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private List<Job> jobList;
    private final List<Job> allJobs;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public JobAdapter(List<Job> jobList, List<Job> allJobs) {
        this.jobList = jobList;
        this.allJobs = allJobs;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemJobBinding itemJobBinding = ItemJobBinding.inflate(layoutInflater, parent, false);
        return new JobViewHolder(itemJobBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.onBind(job);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    void updateJob(Job job, int position) {
        allJobs.set(allJobs.indexOf(jobList.get(position)), job);
        jobList.set(position, job);
        notifyDataSetChanged();
    }

    void deleteJob(int position) {
        allJobs.remove(jobList.get(position));
        jobList.remove(position);
        notifyDataSetChanged();
    }

    void addJob(Job job) {
        allJobs.add(job);
        jobList.add(job);
        notifyDataSetChanged();
    }

    void searchJob(String text) {
        jobList.clear();
        if (text.isEmpty()) {
            jobList.addAll(allJobs);
        } else {
            for (Job job : allJobs) {
                if (job.getName().toLowerCase().contains(text.toLowerCase())) {
                    jobList.add(job);
                }
            }
        }
        notifyDataSetChanged();
    }


    class JobViewHolder extends RecyclerView.ViewHolder {
        private final ItemJobBinding binding;

        public JobViewHolder(ItemJobBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Job job) {
            binding.tvName.setText(job.getName());
            binding.description.setText(job.getDescription());
            binding.timeTV.setText(format.format(job.getTime()));
            if (job.getMale()) {
                binding.imgJob.setImageResource(R.drawable.ic_male);
            } else {
                binding.imgJob.setImageResource(R.drawable.ic_female);
            }
            binding.removeTV.setOnClickListener(v -> {
                deleteJob(getAdapterPosition());
            });
            binding.getRoot().setOnClickListener(v -> {
                ((MainActivity) v.getContext()).setJobInputFromAdapter(job, getAdapterPosition());
            });
        }
    }
}